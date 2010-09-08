package com.bekk.wa.webframework.database.util;

import org.apache.log4j.Logger;
import org.dbunit.DefaultDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.impl.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.InputStream;
import java.sql.Connection;

/**
 */
public class DbLoader {
    private EntityManagerFactory entityManagerFactory;
    private Logger logger = Logger.getLogger(getClass());
    private IDatabaseTester databaseTester;

    public void loadData() throws Exception {
        initializeDatabaseTester();
        databaseTester.onSetup();
    }

    private void initializeDatabaseTester() throws Exception {
        logger.debug("initializeDatabaseTester");
        IDatabaseConnection dbunitConn = null;
        EntityManager em;
        try {
            em = entityManagerFactory.createEntityManager();
            SessionImpl hibernateSession = (SessionImpl) em.getDelegate();
            Connection conn = hibernateSession.connection();
            conn.setAutoCommit(true);
            dbunitConn = new DatabaseConnection(conn);

            IDataSet dataSet = loadDataSet(getInitializationDataSetPath());
            databaseTester = new DefaultDatabaseTester(dbunitConn);
            databaseTester.setDataSet(dataSet);
            databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
            databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
        } catch (Exception ex) {
            logger.debug("Exception in initializing database", ex);
            throw ex;
        } finally {
            if (dbunitConn != null) {
                dbunitConn.close();
            }
        }
    }

    private IDataSet loadDataSet(String path) throws Exception {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream stream = cl.getResource(path).openStream();
        return new FlatXmlDataSet(stream);
    }

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
    }

    public String getInitializationDataSetPath() {
        return "events.db.xml";
    }

}
