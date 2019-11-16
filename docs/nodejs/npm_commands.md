# NPM Basics

## Basic commands

**Create a package.json**

npm init --yes  (For non interactive mode)

npm init (for interactive mode)

**Add dependency to package.json**

npm install express

or

npm i express

**Add a Dev dependency**

npm i -D nodemon

**install package globally**

npm i -g create-react-app


## Semantic Versioning

1.6.3

* 1 - major version (breaking changes) - major features
* 6 - minor version (foreward compartible) - new feature with backward compatible
* 3 - Patch (bug fixes) -

**Range of acceptable versions in package.json-**

* ~1.6.3 - any version of 1.6.x is acceptable and should be greater then 1.6.3
* ^1.6.3 - any version of 1.x.y is acceptable where x>=6 

Calculator : https://semver.npmjs.com/

## Publish a NPM Package

* Register with npmjs.com
* from command line - `npm login`
* enter credentials
* create a package.json using `npm init` and provide the package name you want to publish
* create index.js and export the function using module.exports

![example](/images/nodejs/npm_package_export.png)