#!/user/bin/env groovy

def call() {
    echo "building the docker image"
    withCredentials([usernamePassword(credentialsId: 'dockerhub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) { 
        sh 'docker build -t ausman96/ausmandev:jma1.0 .'            // build image here.  ausman96 is account name, ausmandev is the docker hub repository name
        sh 'echo $PASS | docker login -u $USER --password-stdin'    // use password as an input (using echo, not actually providing live input)
        sh 'docker push ausman96/ausmandev:jma1.0'
    }   
}