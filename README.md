# cbq
Code Base API Query Tool

## install
Create src/main/resources/application.conf using application.conf.sample as a guide. You can find your api key and
username in the codebase settings->My Profile page.

    ~~~ sh
    $ ./gradlew installdist
    ~~~

Put the main script in your path
    ~~~ sh
    $ cp build/install/cbq/bin/cbq ~/bin/
    ~~~

Put the libs where the main script wants to find them. By default, it expects to see a lib folder in your home.
    ~~~ sh
    $ cp build/install/cbq/lib/* ~/lib/
    ~~~

## run
    ~~~ sh
    $ cbq previews
    ~~~
