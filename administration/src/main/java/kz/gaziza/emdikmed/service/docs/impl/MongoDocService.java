package kz.gaziza.emdikmed.service.docs.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import kz.gaziza.emdikmed.config.MongoConfig;
import kz.gaziza.emdikmed.service.docs.IMongoDocService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStream;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class MongoDocService implements IMongoDocService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDocService.class);

   @Autowired
   private GridFsOperations gridFsOperations;
   @Autowired
   private GridFsTemplate gridFsTemplate;
   @Autowired
   private MongoDbFactory mongoDbFactory;
   @Autowired
   private MongoConfig mongoConfig;

   /**
    * Пример работы с GridFS (чтение файлов по id)
    */
   @Override
   public GridFsResource downloadScanDoc(String fileId) {
         GridFSFile file = gridFsTemplate.findOne(query(where("_id").is(fileId)));
         if (file != null)
            return new GridFsResource(file, getGridFs().openDownloadStream(file.getObjectId()));
         else
            return null;
   }

   /**
    * Пример работы с GridFS (сохранение файлов)
    */
   @Override
   public String saveScanDoc(InputStream content, String mimeType, String fileName) {
         // Добавляем свою произвольную информацию к файлу
         DBObject metaData = new BasicDBObject();
         metaData.put("type", "video");
         metaData.put("kind", "video");
         metaData.put("mimeType", mimeType);
         LOGGER.debug("[saveUserPicture()] - mimeType: " + mimeType);
         return gridFsOperations.store(content, fileName , mimeType, metaData).toString();

   }

   @Override
   public void deleteScanDoc(String id) {
         Query query = new Query();
         query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
         gridFsOperations.delete(query);
   }

   /**
    * Метод возвращает bucket(таблицу) где хранятся файлы
    * по умолчанию bucket не берется из MongoDatabase, хотя в MongoConfig классе установка этого значения реализована
    */
   private GridFSBucket getGridFs() {
         MongoDatabase db = mongoDbFactory.getDb();
         return GridFSBuckets.create(db, mongoConfig.getDefaultBucket());
   }
}
