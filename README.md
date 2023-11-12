# PredictItStreamApplication
Processes data from predictIt and Twitter to google cloud postgres database with kafka. 
PredictIt is a website where you can bet on the outcome of political events, called markets, by buying shares.  
Data is processed from the PredictIt api into a table in the postgres database each 10 Minutes with kafka. The data from Twitter
is processed each hour. The rest-api connector used is from the community project at 
https://github.com/llofberg/kafka-connect-rest . 

The idea is then to combine the data from the two sources in a plot see my other project:
https://github.com/leoseg/Display-PredictIt-Data-Webpage

## Data Streamed

### PredictIt
Data is processed of 5 markets of europe:
  - Next president of italia ,id :7663
  - Next president of france ,id :7360
  - Boris stays as prime minister ,id :7665
  - Next president of hungary ,id :7636
  - Next leader in europe who is out: 7643

For each market the 5 best contracts with their trading prices are stored all 10 minutes. A contract presents a outcome of a 
market. Visit https://www.predictit.org/ for more information. 

### Twitter
The data from Twitter is from the official Twitter-Developer-Api. Each hour the tweetcount of a hashtag is queried and stored in the database
along with its timestamp and the tweetcount of the last 7 days.
The tweetcount log data for 7 hashtags is stored:
- \#PecresseDetresse
- \#MarinePrésidente
- \#MacronPresidentDesJeunes 
- \#JohnsonOut		   
- \#bolsonaroPresidenteAte2026 
- \#LulaPresidente2022		
- \#PetroPresidente		

## Deploying

### Local deploy
Pull the repository to your machine.
To deploy the application local provide the secrets for your google cloud postgres database in te secrets_template.env file and a creds.json with the google authentication data.
Then you can run the script "deploy_local.sh".

### Deploy on Google-Cloud-Instance
For deploying on a GCI first install a self hosted runner along docker on a google cloud instance. Then set the corresponding secrets needed in the actions and finally run the build action and after that the deploy action. This will deploy the setup onlye one ec2 for testing purposes for a complete setup all instaces should be run as own services. 
