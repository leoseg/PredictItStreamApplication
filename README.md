# PredictItStreamApplication
Streams data from predictIt to google cloud postgres database with kafka. 

Data is streamed from the PredictIt api into a table in the postgres database each 10 Minutes with kafka. 

## Data Streamed


## Deploying
The rest-api connector used is from the community project at 
https://github.com/llofberg/kafka-connect-rest . To use it for this project pull the kafka-connect-rest repository, build the jars and put them into the "rest-connectors" folder.

### Local deploy

To deploy the application local provide the secrets for your google cloud postgres database in te secrets_template.env file and a creds.json with the google authentication data.
Then you can run the script "deploy_local.sh".

### Deploy on instance

Install docker and docker-compose on your instance. Then install a self-hosted git-actions-runner on the instance and execute the github-actions workflow deploy. 
Be sure to provide the secrets need in your github repository. Also only add a self-hosted runner to a private repository for security reasons.
