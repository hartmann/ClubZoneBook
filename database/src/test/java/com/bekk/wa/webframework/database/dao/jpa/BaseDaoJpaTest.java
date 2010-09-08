package com.bekk.wa.webframework.database.dao.jpa;

import com.bekk.wa.webframework.database.dao.BaseDao;
import com.bekk.wa.webframework.database.domain.Location;
import com.bekk.wa.webframework.database.utils.DbUnitTestBase;
import org.junit.Test;

/**
 * Tests the base dao jpa implemetation by using a BaseDao<Portfolio, Integer>.
 * The fact that this class is using Portfolio is of no consequence, it just
 * needed some type. Some of the tests take advantage of a known bit of data for
 * the Portoflio though so if you change it to use a different type you must
 * update the values.
 *
 */
public class BaseDaoJpaTest extends DbUnitTestBase {
    private BaseDao<Location, Integer> testDao;

    /**
     * The data, this method is used by the DbUnitTestBase class to load the data
     * base with known data.
     */
    @Override
    public String getInitializationDataSetPath() {
        return "events.db.xml";
    }

    @Test
    public void dummyTest() {
        assert (true);
    }

    public void setTestDao(BaseDao<Location, Integer> dao) {
        this.testDao = dao;
    }

}
