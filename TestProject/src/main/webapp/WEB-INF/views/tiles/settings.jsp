<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="fixed-plugin">
	<a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
		<i class="material-icons py-2">settings</i>
	</a>
	<div class="card shadow-lg">
		<div class="card-header pb-0 pt-3">
			<div class="float-start">
				<h5 class="mt-3 mb-0">Material UI Configurator</h5>
				<p>See our dashboard options.</p>
			</div>
			<div class="float-end mt-4">
				<button class="btn btn-link text-dark p-0 fixed-plugin-close-button">
					<i class="material-icons">clear</i>
				</button>
			</div>
		</div>
		<hr class="horizontal dark my-1">
		<div class="card-body pt-sm-3 pt-0">
			<div>
				<h6 class="mb-0">Sidebar Colors</h6>
			</div>
			<a href="javascript:void(0)" class="switch-trigger background-color">
				<div class="badge-colors my-2 text-start">
					<span class="badge filter bg-gradient-primary active"
						data-color="primary" onclick="sidebarColor(this)"></span> <span
						class="badge filter bg-gradient-dark" data-color="dark"
						onclick="sidebarColor(this)"></span> <span
						class="badge filter bg-gradient-info" data-color="info"
						onclick="sidebarColor(this)"></span> <span
						class="badge filter bg-gradient-success" data-color="success"
						onclick="sidebarColor(this)"></span> <span
						class="badge filter bg-gradient-warning" data-color="warning"
						onclick="sidebarColor(this)"></span> <span
						class="badge filter bg-gradient-danger" data-color="danger"
						onclick="sidebarColor(this)"></span>
				</div>
			</a>
			<hr class="horizontal dark my-3">
			<div class="mt-2 d-flex">
				<h6 class="mb-0">Light / Dark</h6>
				<div class="form-check form-switch ps-0 ms-auto my-auto">
					<input class="form-check-input mt-1 ms-auto" type="checkbox"
						id="dark-version" onclick="darkMode(this)">
				</div>
			</div>
			<hr class="horizontal dark my-sm-4">
		</div>
	</div>
</div>