# PredictItStreamApplication
Streams data from predictIt to google cloud postgres database with kafka. 

Data is streamed from the PredictIt api into a table in the postgres database each 10 Minutes with kafka. The rest-api connector used is from the community project at 
https://github.com/llofberg/kafka-connect-rest . 
## Data Streamed


## Deploying

### Local deploy
Pull the repository to your machine.
To deploy the application local provide the secrets for your google cloud postgres database in te secrets_template.env file and a creds.json with the google authentication data.
Then you can run the script "deploy_local.sh".

### Deploy on instance
Copy the repository to your github account. 
Install docker and docker-compose on your instance. Then install a self-hosted git-actions-runner on the instance and execute the github-actions workflow deploy at your github repository page under actions. 
Be sure to provide the secrets need in your github repository. Also only add a self-hosted runner to a private repository for security reasons.
