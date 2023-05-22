package kr.mjc.kys.web2023;

import jakarta.servlet.descriptor.JspPropertyGroupDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
@Component
@Slf4j
public class TomcatServerCustomizer
        implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.getTomcatContextCustomizers().add(context -> {
            JspPropertyGroup properties = new JspPropertyGroup();
            properties.addUrlPattern("*.jsp");
            properties.setPageEncoding("UTF-8");

            Collection<JspPropertyGroupDescriptor> descriptors = new HashSet<>();
            descriptors.add(new JspPropertyGroupDescriptorImpl(properties));

            context.setJspConfigDescriptor(
                    new JspConfigDescriptorImpl(descriptors, new HashSet<>()));

            context.setJspConfigDescriptor(
                    new JspConfigDescriptorImpl(descriptors, new HashSet<>()));
            context.addWelcomeFile("index.html");
        });
        log.info("Tomcat customized");
    }
}
