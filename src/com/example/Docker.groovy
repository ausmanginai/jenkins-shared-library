#!/user/bin/env groovy

package com.example

class Docker implements Serializable{
    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imagename) {
        script.echo "building the docker image"
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "docker build -t $imagename ."
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"
            script.sh "docker push $imagename"
        }
    }
}