package com.suspendfun

import IntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.UUID

@IntegrationTest
class DemoApplicationTests {
    @Autowired
    private lateinit var mockMvc: MockMvc

    // @MockkBean(relaxed = true)
    // private lateinit var csvService: CsvService

    @Test
    fun contextLoads() {
    }

    @Test
    fun `should return csv`() {
        val organisationId = UUID.randomUUID()
        mockMvc.perform(
            MockMvcRequestBuilders.get("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .param("organisationId", organisationId.toString())
        ).andExpect(MockMvcResultMatchers.status().isOk)

        // verify { csvService.generate() }
    }
}