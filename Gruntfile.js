'use strict';

module.exports = function (grunt) {

    require('load-grunt-tasks')(grunt);
    require('time-grunt')(grunt);

    grunt.initConfig({
        APP: {
            app: require('./bower.json').appPath || 'app',
            dist: 'src/main/webapp/app'
        },
        useminPrepare: {
            html: 'src/main/webapp/**/*.html',
            options: {
                dest: '<%= APP.dist %>'
            }
        },
        usemin: {
            html: ['<%= APP.dist %>/**/*.html'],
            css: ['<%= APP.dist %>/styles/**/*.css'],
            options: {
                assetsDirs: ['<%= APP.dist %>/**/'],
                dirs: ['<%= APP.dist %>']
            }
        },
        uglify: {
            dist: {
                files: {
                    '<%= APP.dist %>/app/scripts.js': [
                        '<%= APP.dist %>/app/scripts.js'
                    ]
                }
            }
        },
        cssmin: {}
    });

    grunt.registerTask('build', [
        'useminPrepare',
        //'concat',
        //'cssmin',
        'uglify',
        'usemin'
    ]);

};
