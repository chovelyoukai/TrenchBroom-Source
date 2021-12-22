#!/bin/bash
java -jar MapToVmf.jar ${@:1:$# - 1} > ${@: -1}
