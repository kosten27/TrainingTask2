package task

class ReportController {

    def reportService

    def index() {
    }

    def create() {
        reportService.generateReport(params)
    }
}
