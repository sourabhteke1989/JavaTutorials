package com.maxlogic.springboottutorial.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.maxlogic.springboottutorial.kafka.config.KafkaConfiguration;
import com.maxlogic.springboottutorial.kafka.config.KafkaTestConfiguration;
import com.maxlogic.springboottutorial.service.IUserService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

import com.maxlogic.springboottutorial.tokenization.DetokenizedData;
import com.maxlogic.springboottutorial.tokenization.DummyTokenizationService;
import com.maxlogic.springboottutorial.tokenization.TokenizedData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {KafkaConfiguration.class, KafkaTestConfiguration.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class TestMyRestController {

  @Value("${maxlogic.config.enabled}")
  protected boolean flag;

  @Value("${maxlogic.config.maxproperty}")
  protected String maxproperty;

  @Autowired IUserService userService;

  @Autowired TestRestTemplate restTemplate;

  @Autowired DummyTokenizationService tokenizationService;

  @Autowired MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();
  private XmlMapper xmlMapper = new XmlMapper();

  private TokenizedData tokenizedDataSetup;
  private static final String TEST_ACCOUNT_NUMBER_1 = "12345678";
  private static final String TEST_ACCOUNT_NUMBER_2 = "123456789";

  @BeforeAll
  public void setupData() {
    userService.createUser(new UserCredentials(0, "abc", "xyz"));
    tokenizedDataSetup = tokenizationService.tokenize(TEST_ACCOUNT_NUMBER_1);
  }

  @Test
  @DisplayName("testSpringBootProperties")
  public void testSpringBootProperties(TestInfo testInfo) {
    log.info("Running [{}]", testInfo.getDisplayName());
    assertThat(flag).isEqualTo(false);
    assertThat(maxproperty).isEqualTo("test_val");
  }

  @Test
  public void testGetUser() {
    UserCredentials userCredentials =
        restTemplate.getForObject("/user?id=1", UserCredentials.class);
    assertThat(userCredentials.getUserName()).isEqualTo("abc");
    assertThat(userCredentials.getPassword()).isEqualTo("xyz");
  }

  @ParameterizedTest
  @CsvSource({"abhijeet, yadav", "arun, nair"})
  public void testCreateUserParameterized(String userName, String password)
      throws URISyntaxException {
    UserCredentials userCredentials = new UserCredentials(0, userName, password);
    RequestEntity<UserCredentials> requestEntity =
        new RequestEntity(userCredentials, HttpMethod.POST, new URI("/createUser"));
    ResponseEntity<UserCredentials> response =
        restTemplate.exchange(requestEntity, UserCredentials.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    UserCredentials responseUser = response.getBody();
    assertThat(responseUser.getUserName()).isEqualTo(userName);
    assertThat(responseUser.getPassword()).isEqualTo(password);

    UserCredentials userCredentials1 =
        restTemplate.getForObject("/user?id=" + responseUser.getId(), UserCredentials.class);
    assertThat(userCredentials1.getUserName()).isEqualTo(userName);
    assertThat(userCredentials1.getPassword()).isEqualTo(password);
  }

  @Test
  public void testCreateUser() throws Exception {
    UserCredentials userCredentials = new UserCredentials(0, "sourabh", "teke");
    RequestEntity<UserCredentials> requestEntity =
        new RequestEntity(userCredentials, HttpMethod.POST, new URI("/createUser"));
    ResponseEntity<UserCredentials> response =
        restTemplate.exchange(requestEntity, UserCredentials.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    UserCredentials responseUser = response.getBody();
    assertThat(responseUser.getUserName()).isEqualTo("sourabh");
    assertThat(responseUser.getPassword()).isEqualTo("teke");

    UserCredentials userCredentials1 =
        restTemplate.getForObject("/user?id=" + responseUser.getId(), UserCredentials.class);
    assertThat(userCredentials1.getUserName()).isEqualTo("sourabh");
    assertThat(userCredentials1.getPassword()).isEqualTo("teke");

    MvcResult mvcResult =
        mockMvc
            .perform(post("/createUserForm").param("userName", "sunny").param("password", "rathod"))
            .andExpect(status().isOk())
            .andReturn();

    UserCredentials sunnyUserRes =
        objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserCredentials.class);
    assertThat(sunnyUserRes.getUserName()).isEqualTo("sunny");
    assertThat(sunnyUserRes.getPassword()).isEqualTo("rathod");

    userCredentials1 =
        restTemplate.getForObject("/user?id=" + sunnyUserRes.getId(), UserCredentials.class);
    assertThat(userCredentials1.getUserName()).isEqualTo("sunny");
    assertThat(userCredentials1.getPassword()).isEqualTo("rathod");

    mvcResult =
        mockMvc
            .perform(get("/user").param("id", String.valueOf(sunnyUserRes.getId())))
            .andExpect(status().isOk())
            .andReturn();
    userCredentials1 =
        xmlMapper.readValue(mvcResult.getResponse().getContentAsString(), UserCredentials.class);
    assertThat(userCredentials1.getUserName()).isEqualTo("sunny");
    assertThat(userCredentials1.getPassword()).isEqualTo("rathod");
  }

  @Test
  void testTokenize() {
    TokenizedData tokenizedData = tokenizationService.tokenize(TEST_ACCOUNT_NUMBER_2);
    System.out.println("Global token:"+tokenizedData.getGlobalToken());
    DetokenizedData detokenizedData = tokenizationService.detokenize(tokenizedData.getGlobalToken());
    assertThat(detokenizedData.getCc()).isEqualTo(TEST_ACCOUNT_NUMBER_2);
  }

  @Test
  void testTokenizeAlreadyExists() {
    TokenizedData tokenizedData = tokenizationService.tokenize(TEST_ACCOUNT_NUMBER_1);
    assertThat(tokenizedData.getGlobalToken()).isEqualTo(tokenizedDataSetup.getGlobalToken());
  }

  @Test
  void testDetokenize() {
    System.out.println("Global token:"+tokenizedDataSetup.getGlobalToken());
    DetokenizedData detokenizedData = tokenizationService.detokenize(tokenizedDataSetup.getGlobalToken());
    assertThat(detokenizedData.getCc()).isEqualTo(TEST_ACCOUNT_NUMBER_1);
  }

  @Test
  void testDetokenizeNotFound() {
    assertThatThrownBy(() -> tokenizationService.detokenize("12345"));
  }
}
