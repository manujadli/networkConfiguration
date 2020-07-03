pipeline {
    agent any

    stages {
        stage('Cleaning Stage') {		
            steps {
			   script {
					try {
						bat "mvn clean"
					}
					catch (err) {
						currentBuild.result = 'UNSTABLE'
						clean_failed()
						throw err
					}
				 }
				
            }
        }

        stage('Print WorkSpace Details') {		
            steps {
			   script {
					try {
						echo "WorkSpace Location Is : ${env.WORKSPACE}"
						def pom_file = readFile "${env.WORKSPACE}/pom.xml"
						def date = new Date()
                   		def data = "Hello World\nSecond line\n" + date
                   		writeFile(file: 'zorg.txt', text: data)
					}
					catch (err) {
						currentBuild.result = 'UNSTABLE'
						clean_failed()
						throw err
					}
				 }
				
            }
        }

        stage('Read File') {
            steps {
				script {
					try {
						echo 'Starting Reading File'
						def data = readFile(file: 'assembly.yml')
						println(data)

					}
					catch (err) {
						currentBuild.result = 'UNSTABLE'
						echo 'Inside catch .. caught exception'
						echo 'Incremental Build has failed!'						
						echo 'Err: Incremental Build failed with Error: ' + err.getLocalizedMessage()	
						test_failed()
						throw new Exception("Testing failed. Something went wrong!")
						
					}
					
				}							
            }
        }

        stage('Create Json') {
            steps {
				script {
					try {
						def someMap = [
              				  'name' : "john",
              				  'surname' : "doe"
          					]
          				 def json = new groovy.json.JsonBuilder()
          				 json "people": someMap
          				 def file = new File("$WORKSPACE/people.json")
          				 file.write(groovy.json.JsonOutput.prettyPrint(json.toString()))
							
					}
					catch (err) {
						currentBuild.result = 'UNSTABLE'
						echo 'Inside catch .. caught exception'
						echo 'Incremental Build has failed!'						
						echo 'Err: Incremental Build failed with Error: ' + err.getLocalizedMessage()	
						test_failed()
						throw new Exception("Testing failed. Something went wrong!")
						
					}
					
				}							
            }
        }


        stage('Packaging Stage') {
            steps {
				script {
					try {
						bat "mvn package -DskipTests"
					}
					catch (err) {
						currentBuild.result = 'UNSTABLE'
						packaging_failed()						
						throw new Exception("Packaging stage failed. Something went wrong!")
					}
				
				}
			    
            }
        }		
		
    }

    post {
        always {
            echo 'One way or another, I have finished'
			echo "RESULT: ${currentBuild.result}"
        }        
    }
}


def test_failed() {
    echo 'Inside test_failed()..'    
}

def clean_failed() {
    echo 'Inside clean_failed()..'    
}

def packaging_failed() {
    echo 'Inside packaging_failed()..'   
}

def drop_email_notification(jira_id) {
	echo 'Inside drop_email_notification()..'
	echo "Build Status RESULT: ${currentBuild.result}"	
	echo "current build number: ${currentBuild.number}"	
	echo "previous build number: ${currentBuild.previousBuild.getNumber()}"
	echo "Build Description: ${currentBuild.getDescription()}"
	echo "Job Name: ${env.JOB_NAME}"		
}
