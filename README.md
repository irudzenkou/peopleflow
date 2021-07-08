# Content
- [How to](#how-to)
- [Specs urls](#specs)
- [Second Part](#second)
- [Third Part](#third)

<a name="how-to"> </a> </a> 

## How to

**Build:**
  go to project root directory and run in terminal

  Linux/Git Bash:

    sh build.sh
  windows:
  
    mvn clean install

**Run:**

  run in terminal or in Git Bash    

    sh run.sh

<a name="specs"> </a> </a> 

## Specs urls

    JSON: http://localhost:8081/v2/api-docs
    Swagger-ui: http://localhost:8081/swagger-ui/

<a name="second"> </a> </a> 

## Second Part

Being concerned about developing high quality, resilient software, giving the fact, that you will be participating, mentoring other engineers in the coding review process.


- Suggest what will be your silver bullet, concerns while you're reviewing this part of the software that you need to make sure is being there.
- What the production-readiness criteria that you consider for this solution

>To review

    1) Project structure should conform to best practices having all layers supposed by it
    2) Code should following known best practises: SOLID, have abstraction layer, loosely coupled, readable, extendable, no code duplications etc.)
    3) Unit tests should be provided for main parts of application
    4) Easy to build, run, deploy, debug

>production-readiness

    1) All changes should be reflected in user stories and passed DoD conditions
    2) Changes passed all testing steps supposed by project rules: Unit, integration, automation, manual, e2e, performance, etc
    3) Solution is tested on security vulnerabilities
    3) Builds and deployments at all environments are done same way via CI/CD tools

<a name="third"> </a> </a> 

## Third Part