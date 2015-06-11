# cbq
Code Base API Query Tool

## install
Create src/main/resources/application.conf using application.conf.sample as a guide. You can find your api key and
username in the codebase settings->My Profile page.

./gradlew installdist

Put the main script in your path
cp build/install/cbq/bin/cbq ~/bin/

Put the libs where the main script wants to find them. I think it expect to see a lib folder in your home.
cp build/install/cbq/lib/* ~/lib/

## run
cbq previews
