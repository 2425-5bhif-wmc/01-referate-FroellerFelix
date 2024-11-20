echo "Stopping container..."

docker stop prometheus
echo "Container 'prometheus' stopped."

docker stop grafana
echo "Container 'grafana' stopped."

docker stop quarkus
echo "Container 'quarkus' stopped."

echo "Deleting container..."

docker rm prometheus
echo "Container 'prometheus' deleted."

docker rm grafana
echo "Container 'grafana' deleted."

docker rm quarkus
echo "Container 'quarkus' deleted."

echo "Containers stopped and deleted."
