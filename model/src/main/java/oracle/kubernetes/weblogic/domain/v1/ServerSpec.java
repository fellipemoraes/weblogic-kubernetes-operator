package oracle.kubernetes.weblogic.domain.v1;

import io.kubernetes.client.models.V1SecretReference;
import java.util.List;

public interface ServerSpec {
  /**
   * Domain unique identifier. Must be unique across the Kubernetes cluster. (Required)
   *
   * @return domain UID
   */
  String getDomainUID();

  /**
   * Domain name (Required)
   *
   * @return domain name
   */
  String getDomainName();

  /**
   * The WebLogic Docker image.
   *
   * <p>Defaults to store/oracle/weblogic:12.2.1.3.
   *
   * @return image
   */
  String getImage();

  /**
   * The image pull policy for the WebLogic Docker image. Legal values are Always, Never and
   * IfNotPresent.
   *
   * <p>Defaults to Always if image ends in :latest, IfNotPresent otherwise.
   *
   * <p>More info: https://kubernetes.io/docs/concepts/containers/images#updating-images
   *
   * @return image pull policy
   */
  String getImagePullPolicy();

  /**
   * Reference to secret containing domain administrator username and password. Secret must contain
   * keys names 'username' and 'password' (Required)
   *
   * @return admin secret
   */
  V1SecretReference getAdminSecret();

  /**
   * Admin server name. Note: Possibly temporary as we could find this value through domain home
   * inspection. (Required)
   *
   * @return admin server name
   */
  String getAsName();

  /**
   * Administration server port. Note: Possibly temporary as we could find this value through domain
   * home inspection. (Required)
   *
   * @return admin server port
   */
  Integer getAsPort();

  /**
   * List of specific T3 channels to export. Named T3 Channels will be exposed using NodePort
   * Services. The internal and external ports must match; therefore, it is required that the
   * channel's port in the WebLogic configuration be a legal and unique value in the Kubernetes
   * cluster's legal NodePort port range.
   *
   * @return exported channels
   */
  List<String> getExportT3Channels();

  /**
   * Controls which managed servers will be started. Legal values are NONE, ADMIN, ALL, SPECIFIED
   * and AUTO.
   *
   * <ul>
   *   <li>NONE indicates that no servers, including the administration server, will be started.
   *   <li>ADMIN indicates that only the administration server will be started.
   *   <li>ALL indicates that all servers in the domain will be started.
   *   <li>SPECIFIED indicates that the administration server will be started and then additionally
   *       only those servers listed under serverStartup or managed servers belonging to cluster
   *       listed under clusterStartup up to the cluster's replicas field will be started.
   *   <li>AUTO indicates that servers will be started exactly as with SPECIFIED, but then managed
   *       servers belonging to clusters not listed under clusterStartup will be started up to the
   *       replicas field.
   * </ul>
   *
   * <p>Defaults to AUTO.
   *
   * @return startup control
   */
  String getStartupControl();
}
