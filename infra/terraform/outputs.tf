output "instance_ip" {
  description = "VM Public IP address"
  value       = google_compute_instance.app.network_interface[0].access_config[0].nat_ip
}
