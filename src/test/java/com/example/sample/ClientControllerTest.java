package com.example.sample;

import com.example.sample.domain.Fund;
import com.example.sample.domain.Investor;
import com.example.sample.request.ClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedRequestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@AppIntegrationTest
class ClientControllerTest {

    @LocalServerPort
    private Integer port;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        webTestClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:" + port)
                .filter(
                        WebTestClientRestDocumentation.documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withResponseDefaults(Preprocessors.prettyPrint())
                                .withRequestDefaults(Preprocessors.prettyPrint()))
                .build();
    }

    @Test
    void test_creating_a_new_client() {
        Fund fund = new Fund();
        List<Fund> funds = new ArrayList<>();
        fund.setDescription("fund1 description");
        fund.setName("fund1");
        fund.setPrice(100);
        funds.add(fund);

        Investor investor = new Investor();
        investor.setName("Twin Turbo");
        investor.setDescription("speed works");
        investor.setFund(funds);


        HashSet<Investor> investors = new HashSet<>();
        investors.add(investor);

        ClientRequest clientrequest = new ClientRequest("TechTron", "Digital Products", investors);
        webTestClient.post()
                .uri("/clients")
                .syncBody(clientrequest)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(
                        WebTestClientRestDocumentation.document(
                                "clients",
                                relaxedRequestFields(
                                        fieldWithPath("name").description("*Required*"),
                                        fieldWithPath("description").description("*Required*"),
                                        fieldWithPath("investors").optional().description("*Optional*")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("The Id of the created Client"),
                                        fieldWithPath("name").description("The name of the Client"),
                                        fieldWithPath("description").description("Client Description"),
                                        fieldWithPath("investors[]").optional().description("investors of client "),
                                        fieldWithPath("investors[].id").optional().description("investors of client "),
                                        fieldWithPath("investors[].name").description("The name of the Cliebnt"),
                                        fieldWithPath("investors[].description").description("Client Description"),
                                        fieldWithPath("investors[].createdAt").description("Client Description"),
                                        fieldWithPath("investors[].fund").description("Client Description"),
                                        fieldWithPath("investors[].fund[].fund_id").description("Client Description"),
                                        fieldWithPath("investors[].fund[].name").description("Client Description"),
                                        fieldWithPath("investors[].fund[].description").description("Client Description"),
                                        fieldWithPath("investors[].fund[].price").description("Client Description"),
                                        fieldWithPath("investors[].fund[].createdAt").description("Client Description"),
                                        fieldWithPath("createdAt").description("createdAt")
                                ))
                );
    }
}
