#!/bin/sh

export LVFOX_DB_DATASOURCE_URL "jdbc:mariadb://127.0.0.1/lvfox?useUnicode=true&characterEncoding=utf8mb4"
export LVFOX_DB_USER "root"
export LVFOX_DB_PASSWORD "pass"
export LVFOX_DB_CONNECTION_TIMEOUT 30000
export LVFOX_DB_MAXIMUM_POOLSIZE 6
export LVFOX_SEARCH_TOKEN "test-search-token"
export LVFOX_POST_TOKEN "test-post-token"
export LVFOX_POST_IP_FILTER "0.0.0.0"
export LVFOX_APPLICATION_SECRET "secretpleasechangeme"
