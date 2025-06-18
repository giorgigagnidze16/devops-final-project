variable "gcp_credentials_file" {
  description = "service account key JSON file"
  type        = string
}

variable "project_id" {
  description = "GCP Project ID"
  type        = string
}

variable "region" {
  description = "GCP region"
  default     = "europe-west3"
}

variable "zone" {
  description = "GCP zone"
  default     = "europe-west3-a"
}

variable "vm_name" {
  description = "VM name"
  default     = "devops-vm"
}

variable "machine_type" {
  description = "GCP machine type"
  default     = "e2-medium"
}

variable "disk_image" {
  description = "image to use"
  default     = "debian-cloud/debian-12"
}

variable "ssh_user" {
  description = "User with ssh access"
  default     = "deployer"
}

variable "ssh_pub_key" {
  description = "Path to SSH public key"
  default     = "~/.ssh/id_rsa.pub"
}
