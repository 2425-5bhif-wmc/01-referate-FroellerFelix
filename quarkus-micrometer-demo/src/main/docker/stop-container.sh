echo "Stopping container..."

docker stop prometheus
echo "Container 'prometheus' stopped."

docker stop grafana
echo "Container 'grafana' stopped."

docker stop quarkus
echo "Container 'quarkus' stopped."

echo "Containers stopped and deleted."
