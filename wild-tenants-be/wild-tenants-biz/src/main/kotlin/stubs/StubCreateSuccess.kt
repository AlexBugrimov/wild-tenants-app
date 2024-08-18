package ru.bugrimov.wt.biz.stubs

import ru.bugrimov.wt.common.WtContext
import ru.bugrimov.wt.common.WtCorSettings
import ru.bugrimov.wt.common.models.WtState
import ru.bugrimov.wt.common.stubs.WtStubs
import ru.bugrimov.wt.lib.cor.ICorChainDsl
import ru.bugrimov.wt.lib.cor.worker
import ru.bugrimov.wt.logging.common.LogLevel
import ru.bugrimov.wt.stubs.WtUbStub

fun ICorChainDsl<WtContext>.stubCreateSuccess(title: String, corSettings: WtCorSettings) = worker {
    this.title = title
    this.description = """
        Кейс успеха для создания квитанции
    """.trimIndent()
    on { stubCase == WtStubs.SUCCESS && state == WtState.RUNNING }
    val logger = corSettings.loggerProvider.logger("stubOffersSuccess")
    handle {
        logger.doWithLogging(id = this.requestId.asString(), LogLevel.DEBUG) {
            state = WtState.FINISHING
            val stub = WtUbStub.prepareResult {
                request.ubMeterReadings.takeIf { it.isNotEmpty() }?.also { this.ubMeterReadings = it }
                request.ubPeriod.also { this.ubPeriod = it }
            }
            response = stub
        }
    }
}
