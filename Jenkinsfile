#!/usr/bin/env groovy

// DeliveryPipeline is defined port jenkins shared lib.
// https://github.ibm.com/tss-devops-sk/quest-tss-jenkins-shared-lib
DeliveryPipeline {
    app_name = 'ra-api'
    // MTM DB configuration
    DB = 'CRED_REFERENCE_ASSET_API_DB_USER_PROD'
    DB_HOST = 'cap-eu-de-prd-sg-bm-04.integration.ibmcloud.com'
    DB_PORT = '15105'
    DB_NAME = 'SPCDB2P'
    DB_SCHEMA = 'WLMPROD'
    LMS_DB_SSL = 'true'
    SSL_KEYSTORE_PASSWORD = 'CRED_TSS_SSL_KEYSTORE_PASSWORD'
}
