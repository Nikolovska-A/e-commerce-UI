# e-commerceAPI

## How to run the project
    1.1 Clone this repository to your local machine: https://github.com/Nikolovska-A/e-commerceAPI.git
    1.2 Open the project in Intellij IDEA.


## Docker
    If you want to run this application with Docker.

### Download and Install Docker
    Make sure that Docker is running. 

### Clone this repository
    1. Copy this project into your local machine.
    2. Open the project using Intellij IDEA.
    3. Go in 'Terminal and write 'docker compose up' (current location should be project location).

### Configure the Remote Debugger in IntelliJ IDEA
    1.1 On your local machine, open IntelliJ IDEA and go to Run > Edit Configurations....
        Click the + button in the top-left corner and select Remote.
    1.2 Give your remote configuration a name - "E-CommerceAPI Remote Debug"
    1.3 The IP address should be "localhost" and the port "5005".
    1.4 Click Apply and OK.
    1.5 Go to Run > Debug... and select the remote configuration you just created.