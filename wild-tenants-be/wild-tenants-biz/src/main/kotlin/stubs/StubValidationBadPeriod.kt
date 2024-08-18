package ru.bugrimov.wt.biz.stubs

import ru.bugrimov.wt.common.WtContext
import ru.bugrimov.wt.common.helpers.fail
import ru.bugrimov.wt.common.models.WtError
import ru.bugrimov.wt.common.models.WtState.RUNNING
import ru.bugrimov.wt.common.stubs.WtStubs.BAD_PERIOD
import ru.bugrimov.wt.lib.cor.ICorChainDsl
import ru.bugrimov.wt.lib.cor.worker

fun ICorChainDsl<WtContext>.stubValidationBadPeriod(title: String) = worker {
    this.title = title
    this.description = """
        Кейс ошибки валидации для периода
    """.trimIndent()
    on { stubCase == BAD_PERIOD && state == RUNNING }
    handle {
        fail(
            WtError(
                group = "validation",
                code = "validation-period",
                field = "period",
                message = "Wrong period field"
            )
        )
    }
}