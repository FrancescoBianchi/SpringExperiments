'use strict';

angular
	.module('app', ['angularFileUpload'])
	.controller('ImageGalleryController', ['$scope', 'FileUploader', function($scope, FileUploader) {
        var uploader = 
    	$scope.uploader = new FileUploader({
            url: 'api/imageGallery/upload',
        });
        
        $scope.validationErrors = []; 

        // allow the selection of only image files
        uploader.filters.push({
            name: 'imageFilter',
            fn: function(item /*{File|FileLikeObject}*/, options) {
                var type = '|' + item.type.slice(item.type.lastIndexOf('/') + 1) + '|';
                return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
            }
        });

        uploader.onErrorItem = function(item, response, status, headers) {
        	console.log("The following error occurred while uploading the file " + item.file.name);
        	console.log(response);
        };
        
        var initAlternativeNamesFields = function(imageItem) {
        	var fileNameWithExtension = imageItem.file.name;
        	var normalizedFileName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));
        	normalizedFileName = normalizedFileName.substring(0, 50);
        	imageItem.caption = normalizedFileName;
        	imageItem.altTag = normalizedFileName;
        };
        
        uploader.onAfterAddingFile = function(imageItem) {
        	imageItem.useFilenameAsDefault = true;
        	initAlternativeNamesFields(imageItem);
        };
        
        $scope.onUseFilenameAsDefaultChanged = function(imageItem) {
        	if(imageItem.useFilenameAsDefault) {
        		initAlternativeNamesFields(imageItem);
        	}
        };
        
        uploader.onBeforeUploadItem = function(imageItem) {
        	imageItem.formData.push({useFilenameAsDefault: imageItem.useFilenameAsDefault});
        	imageItem.formData.push({caption : imageItem.caption});
        	imageItem.formData.push({altTag : imageItem.altTag});
        };
        
        $scope.$on('validityFieldEmit', function(evt, args) {
			 if(args.isValid) {
				  $scope.validationErrors.splice($scope.validationErrors.indexOf(args.element), 1); 
			 } else {
				 $scope.validationErrors.push(args.element);
			 }
		 });
	}]);
