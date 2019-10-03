package task

import TrainingTask2.ReportService

class ReportController {

    ReportService reportService

    def index() {
    }

    def create() {
        reportService.generateReport(params)
    }
}
