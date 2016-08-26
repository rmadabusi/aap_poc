/**
 * Project Name: pet_utilities_common
 * Package Name: com.adoptapet.utilities.dao.impl
 * Class Name: PetUtilitiesDao.java
 * Description:
 *
 *
 * Created Date:Aug 24, 2016
 * Modified Date:Aug 24, 2016
 *
 * Copyright to Treselle
 */
package com.adoptapet.utilities.dao.impl;

import java.util.List;

import org.junit.Test;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class PetUtilitiesTestDao {
    
    public static final String VALID_FAILURE_MESSAGE              = "Valid Input but returning  null objects";
    public static final String VALID_EXCEPTIONS_TRUE_MESSAGE      = "Valid Input but showing exceptions";
    public static final String INVALID_EXCEPTIONS_FALSE_MESSAGE   = "Invalid Input but Not Throwing Any Exceptions";
    public static final String INVALID_FAILURE_MESSAGE            = "Invalid Input but returning  objects"; 
    public static final String RESPONSE                           = "response";

    private static final Logger LOG  = Logger.getLogger(PetUtilitiesTestDao.class);
    private static boolean      thrown = false;
	    
    @Autowired
    private PetUtilitiesDao     petUtilitiesDao;
    
    List<Integer> validMockIDs;
    List<Integer> invalidMockIDs;
    
    /**
     * Initialize connection and mock API 
     * @since August 25, 2016
     */ 
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        PetUtilitiesTestDao.LOG.info("setUpBeforeClass:");
        // Default connection
        petUtilitiesDao = new PetUtilitiesTestDao(null,null,null);    
        validMockIDs =new ArrayList<Integer>();
        validMockIDs.add(1);
        validMockIDs.add(2);
        validMockIDs.add(3);              
    }
    /**
     * This test case is used to test the getstatus method 
     * getstatus method returns the status about api (alive or not) 
     * @since August 25, 2016
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetStatus() {
        
        PetUtilitiesTestDao.LOG.info("testGetStatus Processing ::::");
        boolean thrown = true;
        try{
            Object response = (Map<String, Object>) this.petUtilitiesDao.getStatus();
            if(response!=null){
                Assert.assertNotNull(VALID_FAILURE_MESSAGE, response);
            } else {
                thrown = false;
                Assert.fail(VALID_FAILURE_MESSAGE);
            }
        } catch (Exception e) {
            thrown = false;
        }
        Assert.assertTrue(VALID_EXCEPTIONS_TRUE_MESSAGE, thrown);      
    }
    /**
     * This test case is used to test the getcolor method 
     * getColor method returns the color name by color id 
     * @since August 25, 2016
     */
    
    @SuppressWarnings("unchecked")
    @Test
    public void testgetColor(){
        
        PetUtilitiesTestDao.LOG.info("testgetColor Processing ::::");
        try{
            Object response = (Map<String, Object>) this.petUtilitiesDao.getColors(validMockIDs);
            if(response!=null){
                Assert.assertNotNull(VALID_FAILURE_MESSAGE, response.get(RESPONSE));
            } else {
                thrown = false;
                Assert.fail(VALID_FAILURE_MESSAGE);
            }
        } catch (Exception e) {
            thrown = false;
        }
        Assert.assertTrue(VALID_EXCEPTIONS_TRUE_MESSAGE, thrown);      
    }
    /**
     * This test case is used to test the getFamily method 
     * getFamily method returns the family name by family id 
     * @since August 25, 2016
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetFamilies(){
        
        PetUtilitiesTestDao.LOG.info("testGetFamilies Processing ::::");
        try{
            Object response = (Map<String, Object>) this.petUtilitiesDao.getFamilies(validMockIDs);
            if(response!=null){
                Assert.assertNotNull(VALID_FAILURE_MESSAGE, response.get(RESPONSE));
            } else {
                thrown = false;
                Assert.fail(VALID_FAILURE_MESSAGE);
            }
        } catch (Exception e) {
            thrown = false;
        }
        Assert.assertTrue(VALID_EXCEPTIONS_TRUE_MESSAGE, thrown);     
    }
    
    
}

