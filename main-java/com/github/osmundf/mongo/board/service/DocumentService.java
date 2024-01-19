package com.github.osmundf.mongo.board.service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.bson.BsonDbPointer;
import org.bson.BsonDocument;
import org.bson.BsonInvalidOperationException;
import org.bson.BsonObjectId;
import org.bson.BsonRegularExpression;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.json.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Document Service.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@Service
public class DocumentService {

  private final ObjectMapper objectMapper;

  private final Codec<Document> codec = new DocumentCodec();

  private final DecoderContext decoderContext = DecoderContext.builder().build();

  /**
   * Constructor for Document Service.
   *
   * @param objectMapper object mapper
   */
  @Autowired
  public DocumentService(final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * Returns if the list contains items that are null.
   *
   * @param list list
   * @return true if the list contains items that are null, false otherwise
   */
  public boolean containsNulls(@NonNull List<?> list) {
    for (var item : list) {
      if (item == null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Convert object node to BSON document.
   *
   * @param type type
   * @param objectNode object node
   * @param defaultValue default value
   * @return BSON document, default value otherwise
   */
  @NonNull
  public BsonDocument toBson(
      @NonNull String type, @Nullable ObjectNode objectNode, @NonNull BsonDocument defaultValue) {
    final var result = toBson(type, objectNode);
    return result != null ? result : defaultValue;
  }

  /**
   * Convert object node to BSON document.
   *
   * @param type type
   * @param objectNode object node
   * @return BSON document, null otherwise
   */
  @Nullable
  public BsonDocument toBson(@NonNull String type, @Nullable ObjectNode objectNode) {
    if (objectNode == null) {
      return null;
    }
    return parseBson(type, objectNode.toString());
  }

  /**
   * Converts a list of object nodes to a list of BSON documents.
   *
   * @param type type
   * @param input list of object node
   * @return a list of BSON documents, null otherwise
   */
  @Nullable
  public List<BsonDocument> toBsons(@NonNull String type, @Nullable List<ObjectNode> input) {
    if (input == null) {
      return null;
    }
    final var list = new ArrayList<BsonDocument>(input.size());
    for (final var item : input) {
      list.add(item != null ? parseBson(type, item.toString()) : null);
    }
    return list;
  }

  /**
   * Converts a BSON document to a document.
   *
   * @param bsonDocument BSON document
   * @return document
   */
  @NonNull
  public Document toDocument(@NonNull BsonDocument bsonDocument) {
    return codec.decode(bsonDocument.asBsonReader(), decoderContext);
  }

  /**
   * Converts an object node to a document.
   *
   * @param type type
   * @param input object node
   * @param defaultValue default value
   * @return object node converted to a document, default value otherwise
   */
  @NonNull
  public Document toDocument(
      @NonNull String type, @Nullable ObjectNode input, @NonNull Document defaultValue) {
    final var result = toDocument(type, input);
    return result != null ? result : defaultValue;
  }

  /**
   * Converts an object node to a document.
   *
   * @param type type
   * @param input object node
   * @return object node converted to a document, null otherwise
   */
  @Nullable
  public Document toDocument(@NonNull String type, @Nullable ObjectNode input) {
    if (input == null) {
      return null;
    }
    try {
      return Document.parse(input.toString());
    } catch (JsonParseException e) {
      final var message = String.format("Failed to parse \"%s\" document.", type);
      throw new ResponseStatusException(BAD_REQUEST, message, e);
    }
  }

  /**
   * Converts a list of object nodes to a list of documents.
   *
   * @param type type
   * @param input list of object nodes
   * @return list of documents, null otherwise
   */
  @Nullable
  public List<Document> toDocuments(@NonNull String type, @Nullable List<ObjectNode> input) {
    if (input == null) {
      return null;
    }
    final var size = input.size();
    final var list = new ArrayList<Document>(size);
    for (var i = 0; i < size; i++) {
      list.add(toDocument(String.format("%s[%d]", type, i), input.get(i)));
    }
    return list;
  }

  /**
   * Converts a document to an object node.
   *
   * @param document document
   * @return document converted to an object node, null otherwise
   */
  @Nullable
  public ObjectNode toObjectNode(@Nullable Document document) {
    if (document == null) {
      return null;
    }
    try {
      return objectMapper.readValue(document.toJson(), ObjectNode.class);
    } catch (JsonProcessingException e) {
      throw new ResponseStatusException(BAD_REQUEST, e.getMessage(), e.getCause());
    }
  }

  /**
   * Converts a BSON value to a JSON node.
   *
   * @param bsonValue BSON value
   * @return BSON value converted to a JSON node, null otherwise
   */
  @Nullable
  public JsonNode toJsonNode(@Nullable BsonValue bsonValue) {
    if (bsonValue == null) {
      return null;
    }
    return switch (bsonValue.getBsonType()) {
      case DOUBLE -> new TextNode("" + bsonValue.asDouble().getValue());
      case STRING -> new TextNode(bsonValue.asString().getValue());
      case DOCUMENT -> toObjectNode(bsonValue.asDocument());
      case ARRAY -> toArrayNode(bsonValue.asArray().getValues());
      case OBJECT_ID -> toObjectNode(bsonValue.asObjectId());
      case BOOLEAN -> BooleanNode.valueOf(bsonValue.asBoolean().getValue());
      case REGULAR_EXPRESSION -> toObjectNode(bsonValue.asRegularExpression());
      case DB_POINTER -> toObjectNode(bsonValue.asDBPointer());
      case JAVASCRIPT_WITH_SCOPE, JAVASCRIPT -> new TextNode(bsonValue.asJavaScript().getCode());
      case DATE_TIME -> new TextNode(
          Instant.ofEpochMilli(bsonValue.asDateTime().getValue()).toString());
      case MAX_KEY -> new TextNode("WHAT!?MIN!");
      case MIN_KEY -> new TextNode("WHAT!?MAX!");
      case NULL,
          DECIMAL128,
          INT64,
          TIMESTAMP,
          INT32,
          SYMBOL,
          BINARY,
          END_OF_DOCUMENT,
          UNDEFINED -> throw new RuntimeException(":" + bsonValue.getBsonType());
    };
  }

  private ArrayNode toArrayNode(List<BsonValue> values) {
    if (values == null) {
      return null;
    }
    if (values.isEmpty()) {
      return new ArrayNode(objectMapper.getNodeFactory(), 0);
    }
    final var arrayNode = new ArrayNode(objectMapper.getNodeFactory(), values.size());
    for (BsonValue item : values) {
      arrayNode.add(toJsonNode(item));
    }
    return arrayNode;
  }

  private ObjectNode toObjectNode(@NonNull BsonDocument document) {
    final var node = new ObjectNode(objectMapper.getNodeFactory());
    for (final var entry : document.entrySet()) {
      node.set(entry.getKey(), toJsonNode(entry.getValue()));
    }
    return node;
  }

  private ObjectNode toObjectNode(@NonNull BsonObjectId objectId) {
    final var node = new ObjectNode(objectMapper.getNodeFactory());
    node.set("$oid", new TextNode(objectId.getValue().toHexString()));
    return node;
  }

  private ObjectNode toObjectNode(@NonNull BsonRegularExpression regularExpression) {
    final var result = new ObjectNode(objectMapper.getNodeFactory());
    final var value = new ObjectNode(objectMapper.getNodeFactory());
    value.set("pattern", new TextNode(regularExpression.getPattern()));
    value.set("options", new TextNode(regularExpression.getOptions()));
    result.set("$regularExpression", value);
    return result;
  }

  private ObjectNode toObjectNode(@NonNull BsonDbPointer dbPointer) {
    final var node = new ObjectNode(objectMapper.getNodeFactory());
    node.set("namespace", new TextNode(dbPointer.getNamespace()));
    node.set("$oid", new TextNode(dbPointer.getId().toHexString()));
    return node;
  }

  @NonNull
  private BsonDocument parseBson(@NonNull String type, @NonNull String input) {
    try {
      return BsonDocument.parse(input);
    } catch (BsonInvalidOperationException | JsonParseException e) {
      final var message = String.format("Failed to parse \"%s\" document. %s", type, input);
      throw new ResponseStatusException(BAD_REQUEST, message, e);
    }
  }
}
