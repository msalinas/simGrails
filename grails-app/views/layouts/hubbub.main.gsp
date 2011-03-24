
<html>
    <head>
        <title>SIM &raquo; <g:layoutTitle default="Bienvenido" /></title>
        <nav:resources/>
        <link rel="stylesheet" href="<g:createLinkTo dir='css' file='sim.css'/>"/>
        <g:javascript library="application" />
        <g:javascript library="scriptaculous"/>
        <g:layoutHead />
    </head>
    <body>
        <div>
            <div id="hd">
                <a href="<g:createLinkTo dir="/"/>"><img id="logo" src="${createLinkTo(dir: 'images', file: 'sim.jpg')}" alt="sim logo"/></a>
            </div>
            <div id="bd"><!-- start body -->
                <nav:render group="tabs"/><br/>
				<nav:renderSubItems group="tabs"/>
            	<g:layoutBody/>    
            </div>  <!-- end body -->
			
            <div id="ft">
                <div id="footerText">
                    SIM - Sistema Integral de Microfinancieras
                </div>
            </div>
        </div>
    </body>
</html>
