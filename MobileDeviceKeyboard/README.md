# MobileDeviceKeyboard

## Installation

Simply download the files and open the files in *src* to view the source code.

## Running

Download MobileDeviceKeyboard.jar and run "java -jar MobileDeviceKeyboard.jar". Make sure your JRE is up to date.

## How to Use

The MobileDeviceKeyboard begins by asking you for a command. It accepts the following commands (it's not case sensitive):

### Train

Accepts any phrase or word to train the AutoCompleteProvider. On a line break, it will take in the recorded text and train the AutoCompleteProvider on those words.

### Input

Accepts any single word or fragment input. Outputs a list of all possible typeahead words recieved from training with their respective confidence levels, ordered by said confidence.

### Exit

Exits the program and ceases running. Note that the AutoCompleteProvider must be retrained each time it's ran. 
