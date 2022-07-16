const Course = artifacts.require("Course");
const Migrations = artifacts.require("Migrations");

module.exports = function(deployer) {
    deployer.deploy(Course);
    deployer.deploy(Migrations);
};