/* 
 * Copyright 2016 King's College London, Richard Jackson <richgjackson@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.kcl.it.postgres;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import uk.ac.kcl.testservices.BiolarkWebserviceWithoutSchedulingTests;
import uk.ac.kcl.utils.PostGresTestUtils;
import uk.ac.kcl.utils.TestUtils;
import uk.ac.kcl.scheduling.SingleJobLauncher;
import uk.ac.kcl.testexecutionlisteners.BasicTestExecutionListenerLargeInsert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author rich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("uk.ac.kcl.it")
@TestPropertySource({
        "classpath:postgres_test.properties",
        "classpath:postgres_db.properties",
//        "classpath:sql_server_test.properties",
//        "classpath:sql_server_db.properties",
        "classpath:jms.properties",
        "classpath:noScheduling.properties",
        "classpath:elasticsearch.properties",
        "classpath:biolark_webservice.properties",
        "classpath:jobAndStep.properties"})
@ContextConfiguration(classes = {
        PostGresTestUtils.class,
        SingleJobLauncher.class,
        TestUtils.class},
        loader = AnnotationConfigContextLoader.class)
@TestExecutionListeners(
        listeners = BasicTestExecutionListenerLargeInsert.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@ActiveProfiles({"webservice","basic","localPartitioning","jdbc_in","jdbc_out","elasticsearchRest","primaryKeyPartition","postgres"})
//@ActiveProfiles({"webservice","basic","localPartitioning","jdbc_in","jdbc_out","elasticsearch","primaryKeyPartition","sqlserver"})
public class BiolarkWebserviceWithoutScheduling {

    @Autowired
    BiolarkWebserviceWithoutSchedulingTests biolarkWebserviceWithoutSchedulingTests;

    @Test
    @DirtiesContext
    @Ignore
    public void biolarkWebserviceWithoutScheduling(){
        biolarkWebserviceWithoutSchedulingTests.biolarkTest();
    }



}
