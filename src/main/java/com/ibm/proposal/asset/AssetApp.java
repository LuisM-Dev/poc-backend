package com.ibm.proposal.asset;

import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
//@SecurityScheme(
//        securitySchemeName = "apiAuth",
//        type = SecuritySchemeType.HTTP,
//        scheme = "bearer",
//        bearerFormat = "JWT",
//        description = "Authentication needed to access the inner API."
//)
public class AssetApp extends Application {
}
