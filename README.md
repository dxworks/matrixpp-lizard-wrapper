# metrixpp-lizard-wrapper

docker run -v ${PWD}:/usr/analysis/sources -v ${PWD}/ceva:/usr/analysis/result -e PROJECT_ID=test2 -it 50207117a4dc

docker run -v ${PWD}:/app/project -v ${PWD}/ceva:/app/dx-results-lizard -it denisudevforfood/lizard:1.0 lizard /app/project --csv

//        -DinputDir="/Users/denisfeier/Documents/node-cu-george"
//        -DoutputDir="/Users/denisfeier/Documents/metrixPP-wrapper/res"
//        -DlizardImageID="93db6ce88c38"
//        -DmetrixppImageID="50207117a4dc"
//        -Dconfig=""