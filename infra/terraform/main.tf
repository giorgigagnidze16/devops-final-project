terraform {
  required_version = ">= 1.5.0"
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "~> 5.0"
    }
  }
}

provider "google" {
  credentials = file(var.gcp_credentials_file)
  project     = var.project_id
  region      = var.region
  zone        = var.zone
}

resource "google_compute_instance" "app" {
  name         = var.vm_name
  machine_type = var.machine_type
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = var.disk_image
    }
  }

  network_interface {
    network = "default"
    access_config {}
  }

  metadata_startup_script = file("startup.sh")

  tags = ["docker-host"]

  metadata = {
    ssh-keys = "${var.ssh_user}:${file(var.ssh_pub_key)}"
  }
}

resource "google_compute_firewall" "default-allow-ssh-http-https" {
  name    = "default-allow-ssh-http-https"
  network = "default"

  allow {
    protocol = "tcp"
    ports    = ["22", "80", "443", "8080", "9090", "9093", "3000", "3001"]
  }

  source_ranges = ["0.0.0.0/0"]
  target_tags   = ["docker-host"]
}
