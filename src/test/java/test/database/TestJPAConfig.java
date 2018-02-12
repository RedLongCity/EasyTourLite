package test.database;

import com.redlongcitywork.easytourlite.dao.CountryDao;
import com.redlongcitywork.easytourlite.dao.CountryDaoImpl;
import com.redlongcitywork.easytourlite.dao.CurrencyDao;
import com.redlongcitywork.easytourlite.dao.CurrencyDaoImpl;
import com.redlongcitywork.easytourlite.dao.From_CitiesDao;
import com.redlongcitywork.easytourlite.dao.From_CitiesDaoImpl;
import com.redlongcitywork.easytourlite.dao.HotToursRequestDao;
import com.redlongcitywork.easytourlite.dao.HotToursRequestDaoImpl;
import com.redlongcitywork.easytourlite.dao.HotToursSessionDao;
import com.redlongcitywork.easytourlite.dao.HotToursSessionDaoImpl;
import com.redlongcitywork.easytourlite.dao.Hotel_RatingDao;
import com.redlongcitywork.easytourlite.dao.Hotel_RatingDaoImpl;
import com.redlongcitywork.easytourlite.dao.MailAddressDao;
import com.redlongcitywork.easytourlite.dao.MailAddressDaoImpl;
import com.redlongcitywork.easytourlite.dao.Meal_TypeDao;
import com.redlongcitywork.easytourlite.dao.Meal_TypeDaoImpl;
import com.redlongcitywork.easytourlite.dao.SearchingRequestDao;
import com.redlongcitywork.easytourlite.dao.SearchingRequestDaoImpl;
import com.redlongcitywork.easytourlite.dao.TourDao;
import com.redlongcitywork.easytourlite.dao.TourDaoImpl;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CountryServiceImpl;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.CurrencyServiceImpl;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.From_CitiesServiceImpl;
import com.redlongcitywork.easytourlite.service.HotToursRequestService;
import com.redlongcitywork.easytourlite.service.HotToursRequestServiceImpl;
import com.redlongcitywork.easytourlite.service.HotToursSessionService;
import com.redlongcitywork.easytourlite.service.HotToursSessionServiceImpl;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingServiceImpl;
import com.redlongcitywork.easytourlite.service.MailAddressService;
import com.redlongcitywork.easytourlite.service.MailAddressServiceImpl;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.service.Meal_TypeServiceImpl;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import com.redlongcitywork.easytourlite.service.SearchingRequestServiceImpl;
import com.redlongcitywork.easytourlite.service.TourService;
import com.redlongcitywork.easytourlite.service.TourServiceImpl;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author redloncity 11/02/2018
 */
@ContextConfiguration
@EnableTransactionManagement
public class TestJPAConfig {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public LocalSessionFactoryBean sessionFactory() {
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(dataSource());
            sessionFactory.setPackagesToScan(new String[]{"com.redlongcitywork.easytourlite.model"});
            sessionFactory.setHibernateProperties(hibernateProperties());
            return sessionFactory;
        }

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/tours_database_lite?useUnicode=true&characterEncoding=utf8");
            dataSource.setUsername("root");
            dataSource.setPassword("cvbn7648");
            return dataSource;
        }

        public Properties hibernateProperties() {
            Properties properties = new Properties();
            properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");
            return properties;
        }

        @Bean
        @Autowired
        public HibernateTransactionManager transactionManager(SessionFactory s) {
            HibernateTransactionManager txManager = new HibernateTransactionManager();
            txManager.setSessionFactory(s);
            return txManager;
        }

        @Bean
        public CountryDao countryDao() {
            return new CountryDaoImpl();
        }

        @Bean
        public CountryService countryService() {
            return new CountryServiceImpl();
        }

        @Bean
        public From_CitiesDao cityDao() {
            return new From_CitiesDaoImpl();
        }

        @Bean
        public From_CitiesService cityService() {
            return new From_CitiesServiceImpl();
        }

        @Bean
        public Meal_TypeDao typeDao() {
            return new Meal_TypeDaoImpl();
        }

        @Bean
        public Meal_TypeService typeService() {
            return new Meal_TypeServiceImpl();
        }

        @Bean
        public Hotel_RatingDao ratingDao() {
            return new Hotel_RatingDaoImpl();
        }

        @Bean
        public Hotel_RatingService ratingService() {
            return new Hotel_RatingServiceImpl();
        }

        @Bean
        public CurrencyDao currencyDao() {
            return new CurrencyDaoImpl();
        }

        @Bean
        public CurrencyService currencyService() {
            return new CurrencyServiceImpl();
        }

        @Bean
        public HotToursRequestDao hotRequestDao() {
            return new HotToursRequestDaoImpl();
        }

        @Bean
        public HotToursRequestService hotRequestService() {
            return new HotToursRequestServiceImpl();
        }

        @Bean
        public TourDao tourDao() {
            return new TourDaoImpl();
        }

        @Bean
        public TourService tourService() {
            return new TourServiceImpl();
        }

        @Bean
        public HotToursSessionDao hotSession() {
            return new HotToursSessionDaoImpl();
        }

        @Bean
        public HotToursSessionService hotSessionService() {
            return new HotToursSessionServiceImpl();
        }

        @Bean
        public MailAddressDao mailDao() {
            return new MailAddressDaoImpl();
        }

        @Bean
        public MailAddressService mailService() {
            return new MailAddressServiceImpl();
        }

        @Bean
        public SearchingRequestDao searchingRequestDao() {
            return new SearchingRequestDaoImpl();
        }

        @Bean
        public SearchingRequestService searchingRequestService() {
            return new SearchingRequestServiceImpl();
        }

    }
}
