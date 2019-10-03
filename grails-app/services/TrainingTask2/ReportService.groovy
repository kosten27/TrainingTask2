package TrainingTask2

import grails.core.GrailsApplication
import grails.plugins.jasper.JasperReportDef
import grails.plugins.jasper.JasperService
import grails.transaction.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.servlet.mvc.GrailsWebRequest
import org.grails.web.util.WebUtils
import task.Post

import javax.servlet.ServletContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Transactional
class ReportService {

    JasperService jasperService
    ServletContext servletContext
    GrailsApplication grailsApplication

    JasperReportDef generateReportDef(GrailsParameterMap params, Locale locale, Map models) {
        return jasperService.buildReportDefinition(params, locale, models)
    }

    HttpServletResponse generateReportResponse(GrailsParameterMap params, Locale locale, Map models) {
        return generateResponse(generateReportDef(params, locale, models))
    }

    HttpServletResponse generateResponse(JasperReportDef reportDef) {
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        HttpServletResponse response = webUtils.getCurrentResponse()
        if (!reportDef.fileFormat.inline && !reportDef.parameters._inline) {
            response.setHeader("Content-Disposition", "inline; filename=\"${reportDef.parameters._name ?: reportDef.name}.${reportDef.fileFormat.extension}\"");
            response.setHeader("Content-Type", "${reportDef.fileFormat.mimeTyp}");
            response.contentType = reportDef.fileFormat.mimeTyp
            response.characterEncoding = "UTF-8"
            response.outputStream << reportDef.contentStream.toByteArray()
        }
        return response
    }

    HttpServletResponse generate(GrailsParameterMap params, Locale locale, Map models) {
        return generateReportResponse(params, locale, models)
    }

    HttpServletResponse generateReport(GrailsParameterMap params) {
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        HttpServletRequest request = webUtils.getCurrentRequest()
        Map result = [:]
        result.data = Post.findAll()
        return generate(params, request.getLocale(), result)
    }
}
