#!groovy

node {
	environment {
		DOCKER_HOST='tcp://127.0.0.1:4243'
	}
	
	stage 'Clone the project'
    git 'https://github.com/manuexcd/tfg'

	stage 'Docker image'
	sh './gradlew clean build buildDocker'
	
	stage 'Docker tag'
	sh 'docker tag tfg manuexcd/tfg'
	
	stage 'Docker push'
	withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
		sh 'docker login -u $USER -p $PASS'
	}
	sh 'docker push manuexcd/tfg'
	sh 'docker rmi tfg:latest manuexcd/tfg:latest'
}