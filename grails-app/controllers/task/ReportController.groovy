package task

class ReportController {

    def reportService

    def index() {
        reportService.testReport()
    }
}
