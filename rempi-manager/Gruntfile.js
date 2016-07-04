module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
   less: {
     bootstrap: {
       files: {
         "src/main/resources/static/css/bootstrap.css": "src/main/resources/less/bootstrap.less"
       }
     }
     },
     watch: {
       scripts: {
         files: ['src/main/resources/less/*.less'],
         tasks: ['build:less'],
         options: {
            interrupt: true,
         },
       },
     }

  });

  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-less');

  // Default task(s).
  grunt.registerTask('build:less', ['less:bootstrap']);
  grunt.registerTask('watch', ['watch:scripts']);

};