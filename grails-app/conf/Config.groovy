import grails.plugins.springsecurity.SecurityConfigType

// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}
	
	//OBTENER LOGS INFO EN UN ARCHIVO Y EN LA CONSOLA
	
	appenders {
		//console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
		file name:'file', file:'/home/miguel/logs/sim/logInfo.log'
	}
	
	root {
		info 'file','stdout'
	}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    warn   'org.mortbay.log'
	
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.sim.usuario.Usuario'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.sim.usuario.SecUserSecRole'
grails.plugins.springsecurity.authority.className = 'com.sim.usuario.SecRole'

//IMPLEMENTACION DE SEGURIDAD A NIVEL Dynamic request maps
grails.plugins.springsecurity.securityConfigType = SecurityConfigType.Requestmap
grails.plugins.springsecurity.requestMap.className = 'com.sim.usuario.Requestmap'

//IMPLEMENTACION DE ROLES A NIVEL Static URL rules
/*
grails.plugins.springsecurity.securityConfigType = SecurityConfigType.InterceptUrlMap
grails.plugins.springsecurity.interceptUrlMap = [
		'/rsConfGpoEmpresa/*': ['ROLE_USER'],
		'/user/*':         		['ROLE_ADMIN']
]
*/

//IMPLEMENTACION DE SEGURIDAD A NIVEL Annotations VER CONTROLADORA SimCatEscolaridadController

//PANTALLAS PARA LOGIN
grails.plugins.springsecurity.auth.loginFormUrl = '/login/login.gsp'
grails.plugins.springsecurity.failureHandler.defaultFailureUrl = '/login/login.gsp'

//REGISTRO DE USUARIO
//post-registration destination url
//grails.plugins.springsecurity.ui.register.postRegisterUrl = '/welcome'

//ATRIBUTOS DEL CORREO PARA REGISTRO
grails.plugins.springsecurity.ui.register.emailBody = '''\
Estimado(a) $user.username,<br/>
<br/>
Usted (o alguien mas pretendiendo ser usted) creo una cuenta con este correo.<br/>
<br/>
Si usted realizó la petición, presione <a href="$url">aquí</a> para finalizar el registro.
'''

grails.plugins.springsecurity.ui.register.emailFrom = 'do.not.reply@localhost'
grails.plugins.springsecurity.ui.register.emailSubject = 'Registro usuario SIM'

//ASIGNACION DE ROLES
grails.plugins.springsecurity.ui.register.defaultRoleNames = [] // no roles
//or
//grails.plugins.springsecurity.ui.register.defaultRoleNames = ['ROLE_CUSTOMER']

//RESETEAR PASSWORD
//post-reset destination url
//grails.plugins.springsecurity.ui.register.postResetUrl = '/reset'

//ATRIBUTOS DEL CORREO PARA RECUPERAR EL PASSWORD
grails.plugins.springsecurity.ui.forgotPassword.emailBody = '''\
Estimado(a) $user.username,<br/>
<br/>
Usted (o alguien mas pretendiendo ser usted) solicitó actualizar su contraseña<br/>
<br/>
Si usted no realizó esta solicitud haga caso omiso de este correo, ningún cambio será aplicado.<br/>
<br/>
Si usted realizó esta petición, presione <a href="$url">aquí</a> para establecer su contraseña.
'''
grails.plugins.springsecurity.ui.forgotPassword.emailFrom = 'do.not.reply@localhost'
grails.plugins.springsecurity.ui.forgotPassword.emailSubject = 'Restablecer contraseña SIM'

//CONFIGURACION DEL PLUGIN mail
grails {
	mail {
	  host = "smtp.gmail.com"
	  port = 465
	  username = "sistema.microfinanciera@gmail.com"
	  password = "rapidsist"
	  props = ["mail.smtp.auth":"true",
			   "mail.smtp.socketFactory.port":"465",
			   "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			   "mail.smtp.socketFactory.fallback":"false"]
 
 } }