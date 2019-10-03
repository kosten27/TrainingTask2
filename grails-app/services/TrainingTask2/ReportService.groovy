package TrainingTask2

import grails.transaction.Transactional
import org.grails.web.util.WebUtils
import task.Post

@Transactional
class ReportService {

    def jasperService
    def servletContext
    def grailsApplication

    def generateReportDef(params, locale, models) {
        def reportDef = jasperService.buildReportDefinition(params, locale, models)
        return reportDef
    }

    def generateReportResponse(params, locale, models) {
        return generateResponse(generateReportDef(params, locale, models))
    }

    def generateResponse(reportDef) {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        def response = webUtils.getCurrentResponse()
        if (!reportDef.fileFormat.inline && !reportDef.parameters._inline) {
            response.setHeader("Content-Disposition", "inline; filename=\"${reportDef.parameters._name ?: reportDef.name}.${reportDef.fileFormat.extension}\"");
            response.setHeader("Content-Type", "${reportDef.fileFormat.mimeTyp}");
            response.contentType = reportDef.fileFormat.mimeTyp
            response.characterEncoding = "UTF-8"
            response.outputStream << reportDef.contentStream.toByteArray()
        }
    }

    def generate(params, locale, models, isDownload = false) {
        if (isDownload) {
            generateReportDef(params, locale, models)
        } else {
            generateReportResponse(params, locale, models)
        }
    }

    def generateReport(params) {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        def request = webUtils.getCurrentRequest()
        def result = [:]
        result.data = Post.findAll()
        def isDownload = false
        generate(params, request.getLocale(), result, isDownload)
    }
}
