<!DOCTYPE html>
<html id="ng-app" ng-app="app">

<head>
<title>Image Gallery Uploader</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="app/css/bootstrap-wowcher.css" />
<link rel="stylesheet" href="app/css/main.css" />
    
<!-- Fix for old browsers -->
<script src="http://nervgh.github.io/js/es5-shim.min.js"></script>
<script src="http://nervgh.github.io/js/es5-sham.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

<script src="http://code.angularjs.org/1.1.5/angular.min.js"></script>
<script src="lib/js/angular-file-upload/angular-file-upload.js"></script>
<script src="app/js/controllers.js"></script>
<script src="app/js/directives.js"></script>

</head>

<body ng-controller="ImageGalleryController" nv-file-drop="" uploader="uploader" filters="imageFilter">

	<div class="container">

		<div class="row select-file-area">

            <div class="page-header">
				<h2>Select the image(s) that you want to add to your gallery</h2>
				<span class="highlight">You can change the list of selected images before they are uploaded</span>
            </div>

			<div class="row well lead select-file-area-content">
				<div id="browse-file" class="col-md-5 select-file-area-component">
					<span>You can search in your filesystem</span>
					<input type="file" nv-file-select="" uploader="uploader" multiple />
				</div>
				<div id="or-file-choosing-method-separator" class="col-md-2 select-file-area-component">
					<span>or</span>
				</div>
				<div id="drop-area" class="col-md-5 select-file-area-component"
					ng-show="uploader.isHTML5">
					<div class="drop-zone text-center" nv-file-over=""
						uploader="uploader">
						<span>drag & drop it here!</span>
					</div>
				</div>
			</div>
		</div>

		<div id="uploaderQueueContainer" class="row" ng-show="uploader.queue.length > 0">
			<div class="col-md-12">

				<span class="h2">
				   <span ng-show="uploader.getNotUploadedItems().length == 0">All files have been uploaded</span>
				   <span ng-show="uploader.getNotUploadedItems().length > 0 && uploader.getNotUploadedItems().length < uploader.queue.length">You must still upload {{uploader.getNotUploadedItems().length}} out of {{ uploader.queue.length}} selected files</span>
				   <span ng-show="uploader.getNotUploadedItems().length == uploader.queue.length">
				       <span ng-show="uploader.queue.length == 1">There is 1 file ready to be uploaded</span>
				       <span ng-show="uploader.queue.length > 1">There are {{ uploader.queue.length}} files ready to be uploaded</span>
			       </span>
			    </span>

				<div>
					<div class="row text-center table-header lead">
						<div class="col-sm-3"><span>Name</span></div>
						<div class="col-sm-4"><span>Info</span></div>
						<div class="col-sm-1"><span>Size</span></div>
						<div class="col-sm-2"><span>Progress</span></div>
						<div class="col-sm-1"><span>Status</span></div>
						<div class="col-sm-1"><span>Actions</span></div>
					</div>

					<div class="row table-row" ng-repeat="image in uploader.queue">
						<form class="form-horizontal" role="form" name="form">
						
							<div class="col-sm-2">
								<div ng-show="uploader.isHTML5"
									ng-thumb="{ file: image._file, height: 100 }"></div>
								<strong>{{ image.file.name }}</strong>
							</div>
							
							<div class="col-sm-5">
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<div class="checkbox">
											<label> <input type="checkbox"
												name="useFilenameAsDefault"
												ng-disabled="image.isReady || image.isUploading || image.isSuccess"
												ng-model="image.useFilenameAsDefault"
												ng-change="onUseFilenameAsDefaultChanged(image)">
												Default filename as alt_tag/caption
											</label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 control-label">Caption</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="caption"
											ng-disabled="image.isReady || image.isUploading || image.isSuccess"
											ng-model="image.caption"
											ng-readonly="image.useFilenameAsDefault"
                                            required
											ng-minlength="3"
                                            ng-maxlength=50
                                            validity-field-emit>
										<span class="ng-invalid error-message" ng-show="form.caption.$error.required">Please provide a caption for this image</span>
                                        <span class="ng-invalid error-message" ng-show="form.caption.$error.minlength">Provide a caption long at least 3 chars</span>
                                        <span class="ng-invalid error-message" ng-show="form.caption.$error.maxlength">Provide a caption no longer than 50 chars</span>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 control-label" nowrap>Alt tag</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="altTag"
											ng-disabled="image.isReady || image.isUploading || image.isSuccess"
											ng-model="image.altTag"
											ng-readonly="image.useFilenameAsDefault"
                                            required
											ng-minlength="3"
                                            ng-maxlength=50
                                            validity-field-emit>
										<span class="ng-invalid error-message" ng-show="form.altTag.$error.required">Please provide an alternative tag for this image</span>
                                        <span class="ng-invalid error-message" ng-show="form.altTag.$error.minlength">Provide an alternative tag long at least 3 chars</span>
                                        <span class="ng-invalid error-message" ng-show="form.altTag.$error.maxlength">Provide a caption no longer than 50 chars</span>
									</div>
								</div>
							</div>
							
							<div class="col-sm-1">
								<span nowrap>{{ image.file.size/1024/1024|number:2 }} MB</span>
							</div>
							
							<div class="col-sm-2">
								<div class="progress" style="margin-bottom: 0;">
									<div class="progress-bar" role="progressbar"
										ng-style="{ 'width': image.progress + '%' }"
										ng-class="{success: image.isSuccess}"></div>
								</div>
							</div>
							
							<div class="col-sm-1 text-center">
								<span ng-show="image.isSuccess"><i class="glyphicon glyphicon-ok success"></i></span>
                                <span ng-show="image.isError"><i class="glyphicon glyphicon-remove"></i></span>
							</div>
							
							<div class="col-sm-1">
								<button type="button" class="btn btn-primary btn-xs btn-block"
									ng-click="image.upload()"
									ng-class="success"
									ng-disabled="image.isReady || image.isUploading || image.isSuccess || form.$invalid"
									title="Upload only this file">
									<span class="glyphicon glyphicon-upload"></span> Upload
								</button>
								<button type="button" class="btn btn-default btn-xs btn-block"
									ng-click="image.remove()"
									ng-disabled="image.isUploading"
                                    title="Remove this file from the the list">
									<span class="glyphicon glyphicon-trash"></span> Remove
								</button>
							</div>
						</form>
					</div>

					<div class="pull-right" id="button-section">
						<button type="button" class="btn btn-primary btn-s"
							ng-click="uploader.uploadAll()"
							ng-disabled="!uploader.getNotUploadedItems().length || validationErrors.length > 0"
							title="Upload all the files in the list that have not been uploaded yet">
							<span class="glyphicon glyphicon-upload"></span> Upload all
						</button>
						<button type="button" class="btn btn-default btn-s"
							ng-click="uploader.clearQueue()"
							ng-disabled="!uploader.queue.length"
							title="Empty the list of files to be uploaded">
							<span class="glyphicon glyphicon-trash"></span> Remove all
						</button>
					</div>

				</div>

			</div>
		</div>
	</div>

</body>
</html>
