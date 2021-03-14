# metrixpp-lizard-wrapper

This tool is a wrapper of lizard and metrixpp used to gather some complexity details about a project. 

##Usage

If you want to run this tool you will need docker and java >= 1.8.

```
java -DinputDir=<PATH_TO_PROJECT> -DoutputDir=<PATH_WHERE_TO_OUTOUT> -DlizardImageID=<LIZARD_DOCKER_IMAGE_ID> -DmetrixppImageID=<METRIXPP_DOCKER_IMAGE_ID> -Dconfig=<PATH_TO_YAML_CONFIG> -jar metrixPP-wrapper-jar-with-dependencies.jar 
```

**NOTE:** 

`-DinputDir` is the only required input property here.

`-DoutputDir` default is `${PWD}/results`

`-DlizardImageID` default is `denisudevforfood/lizard:1.1`

`-DmetrixppImageID` default is `dxworks/metrixpp:1.4`

##Config File

YAML example config file:

```yaml
config:
  lizard:
    - key: "key1"
      value: "value1"
    - key: "key2"
      value: "value2"
    - key: "key3"
      value: "value3"
  metrixpp:
    - key: "key1"
      value: "value1"
    - key: "key2"
      value: "value2"
    - key: "key3"
      value: "value3"
```

##Docker Containers

For Metrixpp: 

```
docker run -v ${PWD}:/usr/analysis/sources -v ${PWD}/results:/usr/analysis/result -e PROJECT_ID=metrixpp -it dxworks/metrixpp:1.4
```

For Lizard:

```
docker run -v ${PWD}:/app/project -v ${PWD}/results:/app/dx-results-lizard -it denisudevforfood/lizard:1.1
```