package ikriti.starone.util;

import ikriti.starone.util.ProjectProperties;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ProjectProperties extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static Properties properties;
	private static ServletContext context;
	private static ApplicationContext applicationContext;
	private static Logger logger = LoggerFactory.getLogger(ProjectProperties.class);

	
	public void init(ServletConfig config)
	{
		try
		{
			context = config.getServletContext();
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
			
			logger.debug("Loading ProjectProperties ...");
			InputStream is = ProjectProperties.class.getResourceAsStream("/project.properties");
			properties = new Properties();
			properties.load(is);

			context.setAttribute("properties", properties);
			is.close();
			logger.info("Loaded ProjectProperties ...");

		}
		catch (Exception e)
		{
			System.out.println("Error while reading ProjectProperties : " + e);
			e.printStackTrace();
		}
	}

	public static Properties getProperties()
	{
		return properties;
	}
	
	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}
}
