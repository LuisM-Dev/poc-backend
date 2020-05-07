#!/bin/bash
# This script file (Unix line ending!) is needed for CloudFoundry only
echo "-------------------------------------------------------------------------"
echo "Pre-Runtime Hook: Installing Liberty features"
export PATH=$PATH:$HOME/.java/jre/bin
echo "    Path: '$PATH'"

$HOME/.liberty/bin/installUtility install webProfile-8.0 microProfile-2.1 --acceptLicense --verbose
echo "-------------------------------------------------------------------------"