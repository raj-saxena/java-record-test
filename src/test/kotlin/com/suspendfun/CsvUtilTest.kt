package com.suspendfun

import com.fasterxml.jackson.dataformat.csv.CsvSchema
import io.kotlintest.matchers.collections.shouldNotBeEmpty
import org.junit.jupiter.api.Test
import org.springframework.util.ResourceUtils

class CsvUtilTest {

    @Test
    fun `should load csv for normal class`() {
        val inputStream = """
            name, status
            Raj, true
        """.trimIndent().byteInputStream()
        val schema = CsvSchema.builder()
            .setSkipFirstDataRow(true)
            .addColumn("name")
            .addColumn("status")
            .build()
            .withoutHeader()
        val rows = CsvUtil.loadCsv(CsvModel::class.java, schema, inputStream)

        rows.shouldNotBeEmpty()
    }

    @Test
    fun `should load csv for record`() {
        val inputStream = """
            name, status
            Raj, false
        """.trimIndent().byteInputStream()
        val schema = CsvSchema.builder()
            .setSkipFirstDataRow(true)
            .addColumn("name")
            .addColumn("status")
            .build()
            .withoutHeader()
        val rows = CsvUtil.loadCsv(CsvRecord::class.java, schema, inputStream)

        rows.shouldNotBeEmpty()
    }

    @Test
    fun `should load csv for record from file`() {
        val inputStream  = ResourceUtils.getFile("classpath:data/file.csv").inputStream()
        val schema = CsvSchema.builder()
            .setSkipFirstDataRow(true)
            .addColumn("name")
            .addColumn("status")
            .build()
            .withoutHeader()
        val rows = CsvUtil.loadCsv(CsvRecord::class.java, schema, inputStream)

        rows.shouldNotBeEmpty()
    }
}
