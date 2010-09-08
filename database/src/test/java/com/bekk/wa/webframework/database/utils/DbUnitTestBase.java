package com.bekk.wa.webframework.database.utils;

import org.dbunit.DefaultDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.impl.SessionImpl;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.InputStream;
import java.sql.Connection;

public abstract class DbUnitTestBase extends SpringJunit4Base {

    protected EntityManagerFactory entityManagerFactory;
    private IDatabaseTester databaseTester;

    @Before
    public void setup() throws Exception {
        if (databaseTester == null) {
            initializeDatabaseTester();
        }
        databaseTester.onSetup();

    }

    @After
    public void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

    private IDataSet loadDataSet(String path) throws Exception {
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream stream = cl.getResource(path).openStream();
        return new FlatXmlDataSet(stream);
    }

    public void initializeDatabaseTester() throws Exception {
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
            databaseTester = new DefaultDatabaseTester(
                    dbunitConn);
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

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
    }

    public abstract String getInitializationDataSetPath();
}
